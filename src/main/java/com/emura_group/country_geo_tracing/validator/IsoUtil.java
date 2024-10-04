package com.emura_group.country_geo_tracing.validator;
import java.util.Set;

public final class IsoUtil {
	public static final Set<String> ISO_COUNTRY_NAMES = Set.of("TOGO", "COTE D'IVOIRE", "BEBIN", "GHANA", "SENEGAL");
	public static final Set<String> ISO_COUNTRY_CODES  = Set.of("TG", "CI", "BE", "GH", "SN");
	public static final String[] ISO_COUNTRY_LANGUAGES = {"fr", "en"};

    private IsoUtil() {}

    public static boolean isValidISOLanguage(String s) {
        return ISO_COUNTRY_NAMES.contains(s);
    }

    public static boolean isValidISOCountry(String s) {
        return ISO_COUNTRY_CODES.contains(s);
    }
}
