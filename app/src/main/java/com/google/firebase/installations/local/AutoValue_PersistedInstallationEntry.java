package com.google.firebase.installations.local;

import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final PersistedInstallation.RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    private AutoValue_PersistedInstallationEntry(String firebaseInstallationId, PersistedInstallation.RegistrationStatus registrationStatus, String authToken, String refreshToken, long expiresInSecs, long tokenCreationEpochInSecs, String fisError) {
        this.firebaseInstallationId = firebaseInstallationId;
        this.registrationStatus = registrationStatus;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.expiresInSecs = expiresInSecs;
        this.tokenCreationEpochInSecs = tokenCreationEpochInSecs;
        this.fisError = fisError;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallation.RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFisError() {
        return this.fisError;
    }

    public String toString() {
        return "PersistedInstallationEntry{firebaseInstallationId=" + this.firebaseInstallationId + ", registrationStatus=" + this.registrationStatus + ", authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", expiresInSecs=" + this.expiresInSecs + ", tokenCreationEpochInSecs=" + this.tokenCreationEpochInSecs + ", fisError=" + this.fisError + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedInstallationEntry)) {
            return false;
        }
        PersistedInstallationEntry that = (PersistedInstallationEntry) o;
        if (this.firebaseInstallationId != null ? this.firebaseInstallationId.equals(that.getFirebaseInstallationId()) : that.getFirebaseInstallationId() == null) {
            if (this.registrationStatus.equals(that.getRegistrationStatus()) && (this.authToken != null ? this.authToken.equals(that.getAuthToken()) : that.getAuthToken() == null) && (this.refreshToken != null ? this.refreshToken.equals(that.getRefreshToken()) : that.getRefreshToken() == null) && this.expiresInSecs == that.getExpiresInSecs() && this.tokenCreationEpochInSecs == that.getTokenCreationEpochInSecs()) {
                if (this.fisError == null) {
                    if (that.getFisError() == null) {
                        return true;
                    }
                } else if (this.fisError.equals(that.getFisError())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((((((h$ ^ (this.firebaseInstallationId == null ? 0 : this.firebaseInstallationId.hashCode())) * 1000003) ^ this.registrationStatus.hashCode()) * 1000003) ^ (this.authToken == null ? 0 : this.authToken.hashCode())) * 1000003) ^ (this.refreshToken == null ? 0 : this.refreshToken.hashCode())) * 1000003) ^ ((int) ((this.expiresInSecs >>> 32) ^ this.expiresInSecs))) * 1000003) ^ ((int) ((this.tokenCreationEpochInSecs >>> 32) ^ this.tokenCreationEpochInSecs))) * 1000003) ^ (this.fisError != null ? this.fisError.hashCode() : 0);
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends PersistedInstallationEntry.Builder {
        private String authToken;
        private Long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private PersistedInstallation.RegistrationStatus registrationStatus;
        private Long tokenCreationEpochInSecs;

        Builder() {
        }

        private Builder(PersistedInstallationEntry source) {
            this.firebaseInstallationId = source.getFirebaseInstallationId();
            this.registrationStatus = source.getRegistrationStatus();
            this.authToken = source.getAuthToken();
            this.refreshToken = source.getRefreshToken();
            this.expiresInSecs = Long.valueOf(source.getExpiresInSecs());
            this.tokenCreationEpochInSecs = Long.valueOf(source.getTokenCreationEpochInSecs());
            this.fisError = source.getFisError();
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFirebaseInstallationId(String firebaseInstallationId) {
            this.firebaseInstallationId = firebaseInstallationId;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus registrationStatus) {
            if (registrationStatus == null) {
                throw new NullPointerException("Null registrationStatus");
            }
            this.registrationStatus = registrationStatus;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setAuthToken(String authToken) {
            this.authToken = authToken;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setExpiresInSecs(long expiresInSecs) {
            this.expiresInSecs = Long.valueOf(expiresInSecs);
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long tokenCreationEpochInSecs) {
            this.tokenCreationEpochInSecs = Long.valueOf(tokenCreationEpochInSecs);
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFisError(String fisError) {
            this.fisError = fisError;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry build() {
            String missing = this.registrationStatus == null ? " registrationStatus" : "";
            if (this.expiresInSecs == null) {
                missing = missing + " expiresInSecs";
            }
            if (this.tokenCreationEpochInSecs == null) {
                missing = missing + " tokenCreationEpochInSecs";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, this.expiresInSecs.longValue(), this.tokenCreationEpochInSecs.longValue(), this.fisError);
        }
    }
}
