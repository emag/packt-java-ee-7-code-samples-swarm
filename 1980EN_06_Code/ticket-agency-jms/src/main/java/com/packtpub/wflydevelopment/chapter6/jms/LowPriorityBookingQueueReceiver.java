package com.packtpub.wflydevelopment.chapter6.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

/**
 * @author Yoshimasa Tanabe
 */
@MessageDriven(name = "LowPriorityBookingQueueReceiver", activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = BookingQueueDefinition.BOOKING_QUEUE),
  @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "priority = 'LOW'")
})
public class LowPriorityBookingQueueReceiver implements MessageListener {

  @Inject
  private Logger logger;

  @Override
  public void onMessage(Message message) {
    try {
      final String text = message.getBody(String.class);
      logger.info("Received message with LOW priority: " + text);
    } catch (JMSException e) {
      logger.severe(e.toString());
    }
  }

}
