package com.anjana.raulpampliega.jooqdemo.model;

public enum ModuleType {
  BG,
  DC,
  KERNO,
  TOT,
  HD,
  AUTH,
  WF,
  AUDIT,
  SNAPSHOTS,
  ALL;

  private ModuleType() {
  }

  public static String toString(ModuleType moduleType) {
    return moduleType != null ? moduleType.name() : null;
  }
}