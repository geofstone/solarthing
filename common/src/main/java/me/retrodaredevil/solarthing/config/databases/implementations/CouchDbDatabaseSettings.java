package me.retrodaredevil.solarthing.config.databases.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.retrodaredevil.couchdb.CouchProperties;
import me.retrodaredevil.solarthing.annotations.JsonExplicit;
import me.retrodaredevil.solarthing.config.databases.DatabaseSettings;
import me.retrodaredevil.solarthing.config.databases.DatabaseType;
import me.retrodaredevil.solarthing.config.databases.SimpleDatabaseType;

import static java.util.Objects.requireNonNull;

@JsonTypeName("couchdb")
@JsonDeserialize(builder = CouchDbDatabaseSettings.Builder.class)
@JsonExplicit
public final class CouchDbDatabaseSettings implements DatabaseSettings {
	public static final DatabaseType TYPE = new SimpleDatabaseType("couchdb");

	@JsonUnwrapped
	private final CouchProperties couchProperties;
	
	public CouchDbDatabaseSettings(CouchProperties couchProperties) {
		this.couchProperties = couchProperties;
	}
	
	public CouchProperties getCouchProperties() {
		return couchProperties;
	}

	@Override
	public DatabaseType getDatabaseType() {
		return TYPE;
	}

	static class Builder {

		@JsonUnwrapped
		@JsonProperty(required = true)
		private CouchProperties couchProperties;

		public CouchDbDatabaseSettings build(){
			return new CouchDbDatabaseSettings(requireNonNull(couchProperties));
		}
	}
}
