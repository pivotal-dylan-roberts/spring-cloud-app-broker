/*
 * Copyright 2016-2018. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.appbroker.workflow.instance;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.appbroker.deployer.BackingApplications;

public class DefaultParametersTransformer implements ParametersTransformer {

	public void transform(BackingApplications backingApps, Map<String, Object> parameters) {
		backingApps.forEach(backingApplication -> {
			final Map<String, String> environment = new HashMap<>();
			final Map<String, String> backingAppEnvironment = backingApplication.getEnvironment();
			if (backingAppEnvironment != null) {
				environment.putAll(backingAppEnvironment);
			}
			if (parameters != null) {
				parameters.forEach((key, value) -> environment.put(key, value.toString()));
			}
			backingApplication.setEnvironment(environment);
		});
	}

}
