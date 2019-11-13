package blq.ssnb.baseconfigure.splash.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 数据操作类
 * ================================================
 * </pre>
 */
@Dao
public interface SplashDao {

    @Query("select * from splashentity where startTime<= :currentTime and :currentTime<=endTime ")
    List<SplashEntity> getSplashList(long currentTime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateSplashInfo(SplashEntity entity);

    @Query("DELETE FROM splashentity WHERE splashID = :splashID")
    void deleteItem(String splashID);

    @Query("DELETE FROM splashentity")
    void clearAll();

}
