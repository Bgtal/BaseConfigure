package blq.ssnb.baseconfigure.splash.db;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.database.SQLException;
import androidx.annotation.NonNull;

import blq.ssnb.snbutil.SnbLog;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * splashDB,使用先调用init方法
 * ================================================
 * </pre>
 */
@Database(entities = {SplashEntity.class}, version = 1 ,exportSchema = false)
public abstract class SplashDatabase extends RoomDatabase {

    private static volatile SplashDatabase mInstance;
    private static final String SNB_SPLASH_DB = "snb_splash";

    public static void init(Context context){
        if (mInstance == null) {
            synchronized (SplashDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SplashDatabase.class,
                            SNB_SPLASH_DB)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    SnbLog.e(">>>>>Splash-db创建");
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    SnbLog.e(">>>>>Splash-db打开");
                                }
                            })
                            .build();
                }
            }
        }
    }

    public static SplashDatabase getInstance() {
        if(mInstance == null){
            throw new SQLException("请先调用 init(context) 方法");
        }
        return mInstance;
    }

    public abstract SplashDao mSplashDao();

}
