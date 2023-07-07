package org.bohdans.LeonBetsParser.domain.model;

import java.util.List;

public interface ContainsPrintableInfo {
    List<? extends ContainsPrintableInfo> getNextLevel();
}
