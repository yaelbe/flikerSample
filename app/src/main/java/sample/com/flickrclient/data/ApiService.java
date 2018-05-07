package sample.com.flickrclient.data;

import sample.com.flickrclient.data.model.PersonInfoResponse;
import sample.com.flickrclient.data.model.RecentPhotosResponse;
import sample.com.flickrclient.data.model.SizesResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String METHOD_GET_RECENT = "flickr.photos.getRecent";
    String METHOD_GET_PERSON_INFO = "flickr.people.getInfo";
    String METHOD_GET_IMAGE_SIZES = "flickr.photos.getSizes";
    String METHOD_GET_FILTER = "flickr.photos.search";

    @GET("rest/")
    Single<RecentPhotosResponse> getCurrentPhotos(@Query("method") String methodName,
                                                  @Query("page") int page,
                                                  @Query("per_page") int parPage);

    @GET("rest/")
    Single<SizesResponse> getImageSizes(@Query("method") String methodName,
                                        @Query("photo_id") String photoId);

    @GET("rest/")
    Single<PersonInfoResponse> getPersonInfo(@Query("method") String methodName,
                                             @Query("user_id") String userId);

    @GET("rest/")
    Single<RecentPhotosResponse> getFilterPhotos(@Query("method") String methodName,
                                                 @Query("text") String filterText,
                                                 @Query("page") int page,
                                                 @Query("per_page") int parPage);
}
