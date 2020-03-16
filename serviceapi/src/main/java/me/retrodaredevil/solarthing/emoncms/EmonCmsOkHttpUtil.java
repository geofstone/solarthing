package me.retrodaredevil.solarthing.emoncms;

import me.retrodaredevil.solarthing.serviceutil.HeaderRequestInterceptor;
import okhttp3.OkHttpClient;

public final class EmonCmsOkHttpUtil {
	private EmonCmsOkHttpUtil(){ throw new UnsupportedOperationException(); }

	public static OkHttpClient.Builder configure(OkHttpClient.Builder builder, String apiKey){
		return builder
				.addInterceptor(new HeaderRequestInterceptor("Authorization", "Bearer " + apiKey));
	}
}
