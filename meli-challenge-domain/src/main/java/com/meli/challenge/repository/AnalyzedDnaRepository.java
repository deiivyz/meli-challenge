package com.meli.challenge.repository;

import com.meli.challenge.business.analyzeddna.dto.TotalsDTO;
import com.meli.challenge.entity.AnalyzedDna;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalyzedDnaRepository extends MongoRepository<AnalyzedDna, String> {

    @Aggregation(
        pipeline = {
            "{'$facet':{ " +
                "'humans': [ " +
                "   {'$count': 'humans'} " +
                "], " +
                "'mutants': [ " +
                "   {'$match': { 'isMutant': true }}, " +
                "   {'$count': 'mutants'} " +
                "] " +
                "}}, "
            ,
            "{'$project': { " +
                "'humans': { '$arrayElemAt': ['$humans.humans', 0] }, " +
                "'mutants': { '$arrayElemAt': ['$mutants.mutants', 0] } " +
            "}} "
        })
    TotalsDTO getStats();

    Optional<AnalyzedDna> findByDna(String[] dna);
}
