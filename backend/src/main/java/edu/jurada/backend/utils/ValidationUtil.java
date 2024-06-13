package edu.jurada.backend.utils;

public final class ValidationUtil {
	private ValidationUtil(){}

	public static boolean isNullValidForType(boolean areRequired, Object... validatedObjects){
		boolean shouldBeNull = !areRequired;
		for (Object object : validatedObjects){
			// if any object is null and shouldn't be
			// or isn't null and should be validation fails
			boolean isNull = (object == null);
			if(isNull != shouldBeNull){
				return false;
			}
		}
		return true;
	}

	public static boolean isStringExistenceValidForType(boolean areRequired, String... strings) {
		boolean shouldBeNull = !areRequired;
		for(String string : strings){
			// first we check if required strings are present
			// and other types strings are not present
			boolean isNull = (string == null);
			if(isNull!=shouldBeNull){{
				return false;
			}}
			// then we check whether the string is blank (invalid for all values of shouldBeNull)
			if(!isNull && string.trim().isEmpty()){
				return false;
			}
		}
		return true;
	}
}
