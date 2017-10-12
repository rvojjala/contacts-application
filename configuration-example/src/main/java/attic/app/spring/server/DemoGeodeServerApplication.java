/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package attic.app.spring.server;

import org.apache.geode.cache.GemFireCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;

/**
 * The ServerApplication class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@SpringBootApplication
@CacheServerApplication(locators = "localhost[10334]")
public class DemoGeodeServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoGeodeServerApplication.class, args);
  }

  @EnableLocator
  @EnableManager
  @Profile("embedded-services")
  static class ServerConfiguration {
  }

  @Bean("Echo")
  public PartitionedRegionFactoryBean<Object, Object> partitionRegion(GemFireCache gemfireCache) {

    PartitionedRegionFactoryBean<Object, Object> partitionRegion = new PartitionedRegionFactoryBean<>();

    partitionRegion.setCache(gemfireCache);
    partitionRegion.setClose(false);
    partitionRegion.setPersistent(false);

    return partitionRegion;
  }
}
