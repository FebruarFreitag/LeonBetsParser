package org.bohdans.LeonBetsParser.service;

import org.bohdans.LeonBetsParser.domain.model.ContainsPrintableInfo;

import java.util.List;

public interface StatsPrinter {

    void printInfo(List<? extends ContainsPrintableInfo> dtoList);
}
