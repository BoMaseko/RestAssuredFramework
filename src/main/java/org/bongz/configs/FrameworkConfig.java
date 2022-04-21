package org.bongz.configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"system:env",
	"file:${user.dir}/src/test/resources/config/config.properties"
	})
public interface FrameworkConfig  extends Config{

	@Key("${environment}.url")
	String url();
	
	String environment();
}
