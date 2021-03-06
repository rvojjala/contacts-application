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

package attic.app.config.gemfire;

import org.apache.geode.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.PeerCacheApplication;
import org.springframework.data.geo.Point;

import example.app.model.Address;

/**
 * The {@link GemFireConfiguration} class is a Spring {@link Configuration} class containing configuration meta-data
 * to configure and bootstrap an embedded GemFire Server peer cache in the application's JVM.
 *
 * @author John Blum
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.config.annotation.PeerCacheApplication
 * @since 1.0.0
 */
@PeerCacheApplication(name = "CachingExampleApplication")
@SuppressWarnings("unused")
public class GemFireConfiguration {

  @Bean(name = "AddressToLatitudeLongitude")
  public LocalRegionFactoryBean<Address, Point> addressToLatitudeLongitudeRegion(Cache gemfireCache) {

    LocalRegionFactoryBean<Address, Point> addressToLatitudeLongitudeRegion = new LocalRegionFactoryBean<>();

    addressToLatitudeLongitudeRegion.setCache(gemfireCache);
    addressToLatitudeLongitudeRegion.setClose(false);
    addressToLatitudeLongitudeRegion.setPersistent(false);

    return addressToLatitudeLongitudeRegion;
  }

  @Bean(name = "LatitudeLongitudeToAddress")
  public LocalRegionFactoryBean<Address, Point> latitudeLongitudeToAddressRegion(Cache gemfireCache) {

    LocalRegionFactoryBean<Address, Point> addressToLatitudeLongitudeRegion = new LocalRegionFactoryBean<>();

    addressToLatitudeLongitudeRegion.setCache(gemfireCache);
    addressToLatitudeLongitudeRegion.setClose(false);
    addressToLatitudeLongitudeRegion.setPersistent(false);

    return addressToLatitudeLongitudeRegion;
  }
}
