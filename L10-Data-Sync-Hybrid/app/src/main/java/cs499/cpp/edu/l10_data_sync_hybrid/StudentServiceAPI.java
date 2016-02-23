package cs499.cpp.edu.l10_data_sync_hybrid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yusun on 2/17/16.
 */
public interface StudentServiceAPI {

    @GET("list/students")
    Call<List<Student>> getStudentList();
}
