package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.TokenResult;

/* JADX INFO: loaded from: classes10.dex */
final class AutoValue_TokenResult extends TokenResult {
    private final TokenResult.ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    private AutoValue_TokenResult(String token, long tokenExpirationTimestamp, TokenResult.ResponseCode responseCode) {
        this.token = token;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp;
        this.responseCode = responseCode;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        return "TokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", responseCode=" + this.responseCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TokenResult)) {
            return false;
        }
        TokenResult that = (TokenResult) o;
        if (this.token != null ? this.token.equals(that.getToken()) : that.getToken() == null) {
            if (this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp()) {
                if (this.responseCode == null) {
                    if (that.getResponseCode() == null) {
                        return true;
                    }
                } else if (this.responseCode.equals(that.getResponseCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((h$ ^ (this.token == null ? 0 : this.token.hashCode())) * 1000003) ^ ((int) ((this.tokenExpirationTimestamp >>> 32) ^ this.tokenExpirationTimestamp))) * 1000003) ^ (this.responseCode != null ? this.responseCode.hashCode() : 0);
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends TokenResult.Builder {
        private TokenResult.ResponseCode responseCode;
        private String token;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(TokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.responseCode = source.getResponseCode();
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setToken(String token) {
            this.token = token;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp);
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult build() {
            String missing = this.tokenExpirationTimestamp == null ? " tokenExpirationTimestamp" : "";
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.responseCode);
        }
    }
}
