package de.mathisneunzig.escpos;

public enum POSSpecialCharacter {
    USD((byte) 0x24), // United States Dollar ($)
    EUR("EUR"), // Euro (€)
    GBP((byte) 0x9C), // British Pound Sterling (£)
    JPY((byte) 0x9D), // Japanese Yen (¥)
    CHF("CHF"), // Swiss Franc (CHF)
    CNY((byte) 0x9D), // Chinese Yuan (¥)
    INR("INR"), // Indian Rupee (₹)
    RUB("RUB"), // Russian Ruble (₽)
    BRL("R$"), // Brazilian Real (R$)
    ZAR("R"), // South African Rand (R)
    HKD("HK$"), // Hong Kong Dollar (HK$)
    PLN("zl"), // Polish Złoty (zł)
    DKK("DKK"), // Danish Krone (DKK)
    NOK("NOK"), // Norwegian Krone (NOK)
    SEK("SEK"), // Swedish Krona (SEK)
    ISK("ISK"), // Icelandic Króna (ISK)
    FOK("kr"), // Faroese Króna (kr)
    AUD("A$"), // Australian Dollar (A$)
    BSD("B$"), // Bahamian Dollar (B$)
    BBD("Bds$"), // Barbadian Dollar (Bds$)
    BZD("BZ$"), // Belize Dollar (BZ$)
    BMD("Ber$"), // Bermudian Dollar (Ber$)
    BND("B$"), // Brunei Dollar (B$)
    CAD("C$"), // Canadian Dollar (C$)
    KYD("CI$"), // Cayman Islands Dollar (CI$)
    XCD("EC$"), // Eastern Caribbean Dollar (EC$)
    FJD("FJ$"), // Fijian Dollar (FJ$)
    GYD("G$"), // Guyanese Dollar (G$)
    JMD("J$"), // Jamaican Dollar (J$)
    KID("$"), // Kiribati Dollar ($)
    LRD("L$"), // Liberian Dollar (L$)
    NAD("N$"), // Namibian Dollar (N$)
    NZD("$NZ"), // New Zealand Dollar ($NZ)
    SGD("S$"), // Singapore Dollar (S$)
    SBD("SI$"), // Solomon Islands Dollar (SI$)
    SRD("SRD"), // Surinamese Dollar (SRD)
    TWD("NT$"), // New Taiwan Dollar (NT$)
    TTD("TT$"), // Trinidad and Tobago Dollar (TT$)
    TVD("TV$"), // Tuvaluan Dollar (TV$)
    USD_ALT("US$"), // United States Dollar (US$)
    ARS("Arg$"), // Argentine Peso (Arg$)
    CLP("Ch$"), // Chilean Peso (Ch$)
    COP("Col$"), // Colombian Peso (Col$)
    CUP("Cu$"), // Cuban Peso (Cu$)
    DOP("RD$"), // Dominican Peso (RD$)
    MXN("Mex$"), // Mexican Peso (Mex$)
    UYU("$U"); // Uruguayan Peso ($U)

    private final byte[] value;

    POSSpecialCharacter(byte value) {
        this.value = new byte[]{value};
    }

    POSSpecialCharacter(String value) {
        this.value = value.getBytes();
    }

    POSSpecialCharacter(byte[] value) {
        this.value = value;
    }

    public byte[] getBytes() {
        return value;
    }
}
