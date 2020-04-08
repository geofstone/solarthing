package me.retrodaredevil.solarthing.solar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.retrodaredevil.solarthing.solar.outback.fx.FXStatusPacket;
import me.retrodaredevil.solarthing.util.JacksonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolarStatusPacketTest {

//	private static final String testJson = "[{\"packetType\":\"FX_STATUS\",\"address\":1,\"inverterCurrent\":1,\"inverterCurrentRaw\":1,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":118,\"outputVoltageRaw\":118,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":2,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":0,\"chksum\":31,\"operatingModeName\":\"Inv On\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"\"},{\"packetType\":\"FX_STATUS\",\"address\":2,\"inverterCurrent\":0,\"inverterCurrentRaw\":0,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":120,\"outputVoltageRaw\":120,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":0,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":32,\"chksum\":27,\"operatingModeName\":\"Inv Off\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"Temp Sensor failed\"},{\"packetType\":\"MXFM_STATUS\",\"address\":3,\"chargerCurrent\":34,\"pvCurrent\":14,\"inputVoltage\":64,\"dailyKWH\":0.6,\"ampChargerCurrent\":0,\"auxMode\":0,\"errorMode\":0,\"chargerMode\":1,\"batteryVoltage\":25.3,\"dailyAH\":0,\"dailyAHSupport\":\"NOT_SUPPORTED\",\"chksum\":59,\"auxModeName\":\"disabled\",\"errors\":\"\",\"chargerModeName\":\"Float\"},{\"packetType\":\"MXFM_STATUS\",\"address\":4,\"chargerCurrent\":26,\"pvCurrent\":11,\"inputVoltage\":66,\"dailyKWH\":0.4,\"ampChargerCurrent\":0,\"auxMode\":0,\"errorMode\":0,\"chargerMode\":2,\"batteryVoltage\":25.2,\"dailyAH\":0,\"dailyAHSupport\":\"NOT_SUPPORTED\",\"chksum\":58,\"auxModeName\":\"disabled\",\"errors\":\"\",\"chargerModeName\":\"Bulk\"},{\"packetType\":\"FX_DAILY\",\"address\":1,\"startDateMillis\":1577170800013,\"dailyMinBatteryVoltage\":23,\"dailyMaxBatteryVoltage\":25.2,\"inverterKWH\":1.1484332,\"chargerKWH\":0,\"buyKWH\":0,\"sellKWH\":0,\"operationalModeValues\":[2],\"errorModeValue\":0,\"warningModeValue\":0,\"miscValue\":8,\"acModeValues\":[0]},{\"packetType\":\"FX_DAILY\",\"address\":2,\"startDateMillis\":1577170800014,\"dailyMinBatteryVoltage\":23.2,\"dailyMaxBatteryVoltage\":25.2,\"inverterKWH\":0.004907392,\"chargerKWH\":0,\"buyKWH\":0,\"sellKWH\":0,\"operationalModeValues\":[0,2],\"errorModeValue\":0,\"warningModeValue\":32,\"miscValue\":8,\"acModeValues\":[0]},{\"packetType\":\"SOURCE\",\"sourceId\":\"default\"},{\"packetType\":\"FRAGMENT_INDICATOR\",\"fragmentId\":1}]";
	private static final String FX_STRING = "{\"packetType\":\"FX_STATUS\",\"address\":1,\"inverterCurrent\":1,\"inverterCurrentRaw\":1,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":118,\"outputVoltageRaw\":118,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":2,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":0,\"chksum\":31,\"operatingModeName\":\"Inv On\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"\"}";
	private static final String FX_STRING_NO_ADDRESS = "{\"packetType\":\"FX_STATUS\",\"inverterCurrent\":1,\"inverterCurrentRaw\":1,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":118,\"outputVoltageRaw\":118,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":2,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":0,\"chksum\":31,\"operatingModeName\":\"Inv On\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"\"}";
	private static final String FX_ARRAY = "[{\"packetType\":\"FX_STATUS\",\"address\":1,\"inverterCurrent\":1,\"inverterCurrentRaw\":1,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":118,\"outputVoltageRaw\":118,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":2,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":0,\"chksum\":31,\"operatingModeName\":\"Inv On\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"\"},{\"packetType\":\"FX_STATUS\",\"address\":2,\"inverterCurrent\":0,\"inverterCurrentRaw\":0,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":120,\"outputVoltageRaw\":120,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":0,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":32,\"chksum\":27,\"operatingModeName\":\"Inv Off\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"Temp Sensor failed\"}]";
	private static final String OUTBACK_JSON = "[{\"packetType\":\"FX_STATUS\",\"address\":1,\"inverterCurrent\":1,\"inverterCurrentRaw\":1,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":118,\"outputVoltageRaw\":118,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":2,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":0,\"chksum\":31,\"operatingModeName\":\"Inv On\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"\"},{\"packetType\":\"FX_STATUS\",\"address\":2,\"inverterCurrent\":0,\"inverterCurrentRaw\":0,\"chargerCurrent\":0,\"chargerCurrentRaw\":0,\"buyCurrent\":0,\"buyCurrentRaw\":0,\"inputVoltage\":0,\"inputVoltageRaw\":0,\"outputVoltage\":120,\"outputVoltageRaw\":120,\"sellCurrent\":0,\"sellCurrentRaw\":0,\"operatingMode\":0,\"errorMode\":0,\"acMode\":0,\"batteryVoltage\":25.2,\"misc\":8,\"warningMode\":32,\"chksum\":27,\"operatingModeName\":\"Inv Off\",\"errors\":\"\",\"acModeName\":\"No AC\",\"miscModes\":\"\",\"warnings\":\"Temp Sensor failed\"},{\"packetType\":\"MXFM_STATUS\",\"address\":3,\"chargerCurrent\":34,\"pvCurrent\":14,\"inputVoltage\":64,\"dailyKWH\":0.6,\"ampChargerCurrent\":0,\"auxMode\":0,\"errorMode\":0,\"chargerMode\":1,\"batteryVoltage\":25.3,\"dailyAH\":0,\"dailyAHSupport\":\"NOT_SUPPORTED\",\"chksum\":59,\"auxModeName\":\"disabled\",\"errors\":\"\",\"chargerModeName\":\"Float\"},{\"packetType\":\"MXFM_STATUS\",\"address\":4,\"chargerCurrent\":26,\"pvCurrent\":11,\"inputVoltage\":66,\"dailyKWH\":0.4,\"ampChargerCurrent\":0,\"auxMode\":0,\"errorMode\":0,\"chargerMode\":2,\"batteryVoltage\":25.2,\"dailyAH\":0,\"dailyAHSupport\":\"NOT_SUPPORTED\",\"chksum\":58,\"auxModeName\":\"disabled\",\"errors\":\"\",\"chargerModeName\":\"Bulk\"}]";
	@Test
	void testSerialize() throws JsonProcessingException {
		ObjectMapper mapper = JacksonUtil.defaultMapper();
		SolarStatusPacket packet = mapper.readValue(FX_STRING, SolarStatusPacket.class);
		assertTrue(packet instanceof FXStatusPacket);
		System.out.println(mapper.writer().writeValueAsString(packet));

		assertThrows(JsonMappingException.class, () -> mapper.readValue(FX_STRING_NO_ADDRESS, SolarStatusPacket.class));

		// We have to use ArrayList and not just List so Jackson knows what implementation to use
		mapper.readValue(FX_ARRAY, mapper.getTypeFactory().constructCollectionType(ArrayList.class, SolarStatusPacket.class));

		// Now let's see if it can parse MX packets as well
		mapper.readValue(OUTBACK_JSON, mapper.getTypeFactory().constructCollectionType(ArrayList.class, SolarStatusPacket.class));
	}
}
