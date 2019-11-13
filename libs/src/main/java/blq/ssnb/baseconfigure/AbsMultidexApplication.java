package blq.ssnb.baseconfigure;

import android.content.Context;
import androidx.multidex.MultiDex;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      支持分包的 application
 * ================================================
 * </pre>
 */
public abstract class AbsMultidexApplication extends AbsApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
