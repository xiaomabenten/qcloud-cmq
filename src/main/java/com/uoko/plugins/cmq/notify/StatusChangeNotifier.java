package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;
import com.uoko.plugins.cmq.event.CMQApplicationStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 状态改变通知
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class StatusChangeNotifier extends AbstractStatusChangeNotifier {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void doNotify(CMQApplicationEvent event) throws Exception {
        if (event instanceof CMQApplicationStatusChangedEvent) {
            System.out.println("StatusChangeNotifier：" + event.toString());
        }
    }
}
