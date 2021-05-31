package com.anjana.raulpampliega.jooqdemo.model;

public enum State {
  DRAFT(false, true, false, false),
  REJECTED(false, true, false, false),
  PENDING(false, false, false, true),
  APPROVED(true, false, false, false),
  DISABLED(true, false, true, false),
  WAITING(false, false, false, true),
  CANCELLED(false, false, false, false),
  DEPRECATED(true, false, false, false),
  EXPIRED(false, false, true, false),
  IMPORTED(false, true, false, false);

  private final boolean active;
  private final boolean draft;
  private final boolean unable;
  private final boolean wfInProgress;

  public boolean isActive() {
    return this.active;
  }

  public boolean isDraft() {
    return this.draft;
  }

  public boolean isUnable() {
    return this.unable;
  }

  public boolean isWfInProgress() {
    return this.wfInProgress;
  }

  private State(boolean active, boolean draft, boolean unable, boolean wfInProgress) {
    this.active = active;
    this.draft = draft;
    this.unable = unable;
    this.wfInProgress = wfInProgress;
  }
}