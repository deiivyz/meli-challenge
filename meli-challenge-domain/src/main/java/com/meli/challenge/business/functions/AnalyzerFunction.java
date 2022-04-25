package com.meli.challenge.business.functions;

import com.meli.challenge.business.builder.BuilderDiagonalLeftDown;
import com.meli.challenge.business.builder.BuilderDiagonalLeftUp;
import com.meli.challenge.business.builder.BuilderDiagonalRightDown;
import com.meli.challenge.business.builder.BuilderDiagonalRightUp;
import com.meli.challenge.business.builder.BuilderVertical;
import com.meli.challenge.business.builder.IBuilderWord;
import com.meli.challenge.business.functions.dto.CurrentDataDTO;
import com.meli.challenge.utils.AnalyzeUtil;
import com.meli.challenge.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerFunction implements Predicate<String[]> {

    /**
     * Interfaz funcional encargada de analizar el ADN en busca de coincidencias.
     * Si se encuentran las coincidencias necesarias para clasificar el ADN como mutante 
     * se retorna la respuesta positiva, sin procesar los demás datos.
     * @param DnaRequestDTO
     */
    public boolean test(String[] dna) {
        int coincidences = 0;

        for (int currentRow = 0; currentRow < dna.length; currentRow++) {

            List<IBuilderWord> builders = new ArrayList<>();
            builders.add(new BuilderVertical(0));
            builders.add(new BuilderDiagonalLeftUp(dna.length - 1));
            builders.add(new BuilderDiagonalLeftDown(dna.length - 1));
            builders.add(new BuilderDiagonalRightDown(0));
            builders.add(new BuilderDiagonalRightUp(0));

            int currentColumn = 0;

            for (String line : dna) {

                CurrentDataDTO currentData = CurrentDataDTO
                    .builder()
                    .line(line)
                    .currentRow(currentRow)
                    .currentColumn(currentColumn)
                    .build();

                for (IBuilderWord builder : builders) {
                    builder.build(currentData);
                }

                currentColumn++;

            }

            coincidences += AnalyzeUtil.validateSequence(dna[currentRow]);

            for (IBuilderWord builder : builders) {
                coincidences += AnalyzeUtil.validateSequence(builder.getWord());
            }

            if (coincidences >= Constants.MINUMIM_MATCHES_REQUIRED) {
                return true;
            }
        }

        return false;
    }

}
