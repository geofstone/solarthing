package me.retrodaredevil.solarthing.influxdb;

import me.retrodaredevil.solarthing.solar.outback.OutbackData;
import org.junit.jupiter.api.Test;

import static me.retrodaredevil.solarthing.influxdb.InfluxDbPacketSaver.getTagKeys;
import static org.junit.jupiter.api.Assertions.*;

class InfluxDbPacketSaverTest {

	@Test
	void test(){
		OutbackData data = () -> 0;
		System.out.println(getTagKeys(data.getClass()));
	}
}
