package blq.ssnb.baseconfigure.splash.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 启动页的缓存对象
 * ================================================
 * </pre>
 */
@Entity
public class SplashEntity {

    /**
     * id
     */
    @PrimaryKey
    @NonNull
    private String splashID;

    /**
     * 资源url
     */
    private String url;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 结束时间
     */
    private long endTime;

    /**
     * 扩展数据
     */
    private String extMsg;

    /**
     * 更新时间
     */
    private long updateTime;


    @NonNull
    public String getSplashID() {
        return splashID;
    }

    public void setSplashID(@NonNull String splashID) {
        this.splashID = splashID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getExtMsg() {
        return extMsg;
    }

    public void setExtMsg(String extMsg) {
        this.extMsg = extMsg;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
