package com.polidea.rxandroidble2;

import android.util.Log;

import androidx.annotation.Nullable;

public class LogOptions {

    @Nullable
    private Integer logLevel;
    @Nullable
    private Integer macAddressLogSetting;
    @Nullable
    private Integer uuidLogSetting;
    @Nullable
    private Boolean shouldLogAttributeValues;
    @Nullable
    private Logger logger;

    private LogOptions(@Nullable Integer logLevel, @Nullable Integer macAddressLogSetting, @Nullable Integer uuidLogSetting,
                       @Nullable Boolean shouldLogAttributeValues, @Nullable Logger logger) {
        this.logLevel = logLevel;
        this.macAddressLogSetting = macAddressLogSetting;
        this.uuidLogSetting = uuidLogSetting;
        this.shouldLogAttributeValues = shouldLogAttributeValues;
        this.logger = logger;
    }

    @Nullable
    public Integer getLogLevel() {
        return logLevel;
    }

    @Nullable
    public Integer getMacAddressLogSetting() {
        return macAddressLogSetting;
    }

    @Nullable
    public Integer getUuidLogSetting() {
        return uuidLogSetting;
    }

    @Nullable
    public Boolean getShouldLogAttributeValues() {
        return shouldLogAttributeValues;
    }

    @Nullable
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String toString() {
        return "LogOptions{"
                + "logLevel=" + logLevel
                + ", macAddressLogSetting=" + macAddressLogSetting
                + ", uuidLogSetting=" + uuidLogSetting
                + ", shouldLogAttributeValues=" + shouldLogAttributeValues
                + ", logger=" + logger
                + '}';
    }

    /**
     * The builder for {@link #LogOptions(Integer, Integer, Integer, Boolean, Logger)}
     */
    public static class Builder {

        @Nullable
        private Integer logLevel;
        @Nullable
        private Integer macAddressLogSetting;
        @Nullable
        private Integer uuidsLogSetting;
        @Nullable
        private Boolean shouldLogAttributeValues;
        @Nullable
        private Logger logger;

        /**
         * Set the log level of the library. General rule:
         * <p>{@link LogConstants#NONE} — nothing will get logged
         * <p>{@link LogConstants#ERROR} — only terminal library errors
         * <p>{@link LogConstants#WARN} — the above plus all events that may be handled gracefully or wrong usage of the API
         * <p>{@link LogConstants#INFO} — the above plus all info needed to understand the BLE interactions and debug user's application
         * <p>{@link LogConstants#DEBUG} — the above plus information needed to debug the library
         * <p>{@link LogConstants#VERBOSE} — the above plus some information about the internal working
         *
         * @param logLevel the log level
         * @return the builder
         */
        public Builder setLogLevel(@LogConstants.LogLevel @Nullable Integer logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * Set how to log mac addresses:
         * <p>{@link LogConstants#NONE} — address will be omitted i.e. 'XX:XX:XX:XX:XX:XX'
         * <p>{@link LogConstants#MAC_ADDRESS_TRUNCATED} — i.e. '00:11:22:33:44:XX'
         * <p>{@link LogConstants#MAC_ADDRESS_FULL} — i.e. '00:11:22:33:44:55'
         *
         * @param macAddressLogSetting the setting
         * @return the builder
         */
        public Builder setMacAddressLogSetting(@LogConstants.MacAddressLogSetting @Nullable Integer macAddressLogSetting) {
            this.macAddressLogSetting = macAddressLogSetting;
            return this;
        }

        /**
         * Set how to log uuids:
         * <p>{@link LogConstants#NONE} — UUID will be omitted i.e. '...'
         * <p>{@link LogConstants#UUIDS_FULL} — the full UUID will be logged
         *
         * @param uuidsLogSetting the setting
         * @return the builder
         */
        public Builder setUuidsLogSetting(@LogConstants.UuidLogSetting @Nullable Integer uuidsLogSetting) {
            this.uuidsLogSetting = uuidsLogSetting;
            return this;
        }

        /**
         * Set how to log byte array values:
         * <p><code>false</code> — all byte arrays will be omitted i.e. '[...]'
         * <p><code>true</code> — all byte arrays will be printed in hex i.e. '[FF, FF, FF, FF]'
         *
         * @param shouldLogAttributeValues the setting
         * @return the builder
         */
        public Builder setShouldLogAttributeValues(@Nullable Boolean shouldLogAttributeValues) {
            this.shouldLogAttributeValues = shouldLogAttributeValues;
            return this;
        }

        /**
         * Set the logger to get the output
         *
         * @param logger the logger
         * @return the builder
         */
        public Builder setLogger(@Nullable Logger logger) {
            this.logger = logger;
            return this;
        }

        public LogOptions build() {
            return new LogOptions(logLevel, macAddressLogSetting, uuidsLogSetting, shouldLogAttributeValues, logger);
        }
    }

    /**
     * Simple logging interface for log messages from RxAndroidBle
     * <p>
     * {@link Builder#setLogger(Logger)}
     */
    public interface Logger {

        /**
         * @param level one of {@link Log#VERBOSE}, {@link Log#DEBUG}, {@link Log#INFO},
         *              {@link Log#WARN}, {@link Log#ERROR}
         * @param tag   log tag, caller
         * @param msg   message to log
         */
        void log(int level, String tag, String msg);
    }
}
