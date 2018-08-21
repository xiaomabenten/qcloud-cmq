package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;
import com.uoko.plugins.cmq.event.CMQApplicationStatusChangedEvent;

/**
 * <p>
 * 抽象出来的状态通知
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public abstract class AbstractStatusChangeNotifier extends AbstractEventNotifier {

    @Override
    protected boolean shouldNotify(CMQApplicationEvent event) {
        if (event instanceof CMQApplicationStatusChangedEvent) {
            System.out.println("AbstractStatusChangeNotifier state is change");
            return true;
        }
        return false;
    }
}
