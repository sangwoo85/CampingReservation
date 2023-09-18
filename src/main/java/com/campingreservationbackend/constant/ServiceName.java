package com.campingreservationbackend.constant;

import com.campingreservationbackend.seviceImpl.ImgingakReservationServiceImpl;
import com.campingreservationbackend.seviceImpl.YungoReservationServiceImpl;

public class ServiceName {

    public enum SERVICE_NAME{


        /** 임진각 평화누리 캠핑장 */
        IMGINGAK("202111160004", ImgingakReservationServiceImpl.class),

        /** 연곡 솔향기 캠핑장 */
        YUNGOKSOL("202111160005", YungoReservationServiceImpl.class)
        ;

        String id;

        Class serviceClass;

        SERVICE_NAME(String id, Class serviceClass){
            this.id = id;
            this.serviceClass = serviceClass;
        }

        public String getId(){
            return id;
        }
        public Class getServiceClass (){return serviceClass; }
        public boolean equal(String CAMP_ID){
            return this.id.equals(CAMP_ID);
        }

        public static SERVICE_NAME getIdBySerivceName(String id){
            SERVICE_NAME[] service_names = SERVICE_NAME.class.getEnumConstants();
            for(SERVICE_NAME service_name : service_names){
                if(service_name.equal(id)){
                    return service_name;
                }
            }
            return null;
        }

    }

}

