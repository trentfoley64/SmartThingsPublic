/**
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 TODO:
 1) Move the thermostats preference to the main parent app, allowing multiple instances of parent
 2) Implement filter change notices
 */

definition(
	name: "Brighter Thermostat Controls",
	singleInstance: true,
	namespace: "trentfoley64",
	author: "A. Trent Foley, Sr.",
	description: "Brighter Thermostat Controls - time, day, and presence sensitive.",
	category: "My Apps",
	iconUrl: "http://www.trentfoley.com/ST/icons/thermostat.png",
	iconX2Url: "http://www.trentfoley.com/ST/icons/thermostat@2x.png",
	iconX3Url: "http://www.trentfoley.com/ST/icons/thermostat@3x.png"
)

preferences {
	page(name: "mainPage", title: "Brighter Thermostat Controls", install: true, uninstall: true, submitOnChange: true) {
		section {
			app(name: "childThermostatControls", appName: "New Brighter Thermostat Control", namespace: "trentfoley64", title: "new thermostat control...", multiple: true)
		}
		section("Filter info", hideable: true) {
			input "filterLastChanged", "date", title: "Date last changed?", required: false
			input "filterMonthsBeforeChange", "number", title: "Lasts how many months?", required: false
		}
	}
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def initialize() {
	childApps.each {child ->
			log.info "Installed Thermostat Rules: ${child.label}"
	}
}