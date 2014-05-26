package org.joolzminer.examples.boot.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedOriginsConstants {

	private AllowedOriginsConstants() {
	}
	
	public static final Set<String> ALLOWED_ORIGINS = new HashSet<>(Arrays.asList(
			new String[] { 	"http://127.0.0.1:9000",
							"http://localhost:9000" }));
	
}
