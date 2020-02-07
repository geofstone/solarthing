package me.retrodaredevil.solarthing.pvoutput.data;

import com.fasterxml.jackson.annotation.*;
import me.retrodaredevil.solarthing.annotations.JsonExplicit;
import me.retrodaredevil.solarthing.pvoutput.SimpleDate;
import me.retrodaredevil.solarthing.pvoutput.SimpleTime;
import me.retrodaredevil.solarthing.pvoutput.WeatherCondition;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonExplicit
public interface AddOutputParamters {
	@JsonProperty("d")
	SimpleDate getOutputDate();

	/**
	 * Normally this should not be null. This is required if the output does not already exist
	 * @return The number of generated watt hours, or null
	 */
	@JsonProperty("g")
	Number getGenerated();
	/** @return The number of exported watt hours*/
	@JsonProperty("e")
	Number getExported();
	/** @return The peak power in watts*/
	@JsonProperty("pp")
	Number getPeakPower();
	@JsonProperty("pt")
	SimpleTime getPeakTime();

	@JsonProperty("cd")
	String getConditionValue();
	default WeatherCondition getCondition(){
		String value = getConditionValue();
		if(value == null){
			return null;
		}
		return WeatherCondition.getConditionFromString(value);
	}

	@JsonProperty("tm")
	Float getMinimumTemperatureCelsius();
	@JsonProperty("tx")
	Float getMaximumTemperatureCelsius();

	@JsonProperty("cm")
	String getComments();

	@JsonProperty("ip")
	Number getImportPeak();
	@JsonProperty("io")
	Number getImportOffPeak();
	@JsonProperty("is")
	Number getImportShoulder();
	@JsonProperty("ih")
	Number getImportHighShoulder();
	@JsonProperty("c")
	Number getConsumption();
}
