package org.bohdans.LeonBetsParser.service;

import org.bohdans.LeonBetsParser.domain.model.ContainsPrintableInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class StatsPrinterImpl implements StatsPrinter {

    private final StringBuilder stringBuilder = new StringBuilder();

    public void printInfo(List<? extends ContainsPrintableInfo> dtoList) {
        printFurtherDetails(dtoList);
        var result = stringBuilder.toString();
        System.out.println(result);
    }

    private void printFurtherDetails(List<? extends ContainsPrintableInfo> dtoList) {
        if (!CollectionUtils.isEmpty(dtoList)) {
            for (ContainsPrintableInfo dto : dtoList) {
                var currentList = dtoList;
                var nextList = dto.getNextLevel();
                while (!Objects.equals(currentList, nextList)) {
                    stringBuilder.append(dto);
                    currentList = nextList;
                    printFurtherDetails(nextList);
                }
            }
        }
    }
}