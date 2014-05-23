/**
    Copyright (C) 2014  AIT / Austrian Institute of Technology
    http://www.ait.ac.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see http://www.gnu.org/licenses/agpl-3.0.html
 */
package at.ac.ait.ubicity.voodoo.impl;

import java.util.Random;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import at.ac.ait.ubicity.commons.broker.events.EventEntry;
import at.ac.ait.ubicity.core.Core;
import at.ac.ait.ubicity.voodoo.VoodooAddOn;

@PluginImplementation
public class VoodooAddOnImpl implements VoodooAddOn {

	private final String name;

	private final Core core;

	private final int uniqueId;

	protected static Logger logger = Logger.getLogger(VoodooAddOnImpl.class);

	public VoodooAddOnImpl() {
		uniqueId = new Random().nextInt();
		try {
			// set necessary stuff for us to ueberhaupt be able to work
			Configuration config = new PropertiesConfiguration(
					VoodooAddOnImpl.class.getResource("/voodoo.cfg"));

			this.name = config.getString("addon.voodoo.name");

		} catch (ConfigurationException noConfig) {
			logger.fatal("Configuration not found! " + noConfig.toString());
			throw new RuntimeException();
		}

		core = Core.getInstance();
		core.register(this);
	}

	@Override
	public final int hashCode() {
		return uniqueId;
	}

	@Override
	public final boolean equals(Object o) {

		if (VoodooAddOnImpl.class.isInstance(o)) {
			VoodooAddOnImpl other = (VoodooAddOnImpl) o;
			return other.uniqueId == this.uniqueId;
		}
		return false;
	}

	@Override
	public boolean shutdown() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void onEvent(EventEntry event, long sequence, boolean endOfBatch)
			throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
