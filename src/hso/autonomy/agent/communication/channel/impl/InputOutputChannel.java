/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.communication.channel.impl;

import java.util.Map;

import hso.autonomy.agent.communication.action.IEffector;
import hso.autonomy.agent.communication.action.IMessageEncoder;
import hso.autonomy.agent.communication.channel.IChannelManager;
import hso.autonomy.agent.communication.channel.IOutputChannel;
import hso.autonomy.agent.communication.perception.IMessageParser;
import hso.autonomy.agent.communication.perception.IPerceptor;
import hso.autonomy.agent.communication.perception.PerceptorConversionException;
import hso.autonomy.util.connection.ConnectionException;
import hso.autonomy.util.connection.IServerConnection;

/**
 *
 * @author kdorer
 */
public abstract class InputOutputChannel extends ConnectionChannel implements IOutputChannel
{
	/** message decoder */
	private final IMessageParser parser;

	/** message encoder */
	private final IMessageEncoder encoder;

	public InputOutputChannel(
			IChannelManager manager, IServerConnection connection, IMessageParser parser, IMessageEncoder encoder)
	{
		super(manager, connection);
		this.parser = parser;
		this.encoder = encoder;
	}

	@Override
	public void sendMessage(Map<String, IEffector> effectors)
	{
		byte[] message = encoder.encodeMessage(effectors);
		sendMessage(message);
	}

	/**
	 * Notification for each message was received on this channel
	 */
	@Override
	protected void onEachMessage(byte[] message) throws ConnectionException
	{
		try {
			Map<String, IPerceptor> rawPerceptors = parser.parseMessage(message);
			publishPerceptors(rawPerceptors);
		} catch (PerceptorConversionException | RuntimeException e) {
			// TODO: we should be able to recognize. At least we should explicitly
			// specify which exceptions we ignore on purpose
			onInvalidData(parser.getErrorString(message));
		}
	}

	protected void publishPerceptors(Map<String, IPerceptor> rawPerceptors)
	{
		getManager().addPerceptors(rawPerceptors);
	}

	/**
	 * Notification when the first message was received on this channel, before
	 * processing it
	 */
	@Override
	protected void onFirstMessage(byte[] message) throws ConnectionException
	{
	}
}
