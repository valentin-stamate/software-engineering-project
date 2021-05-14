package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.notification.NotificationModel;
import com.bfourclass.euopendata.notification.NotificationService;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelControllerTest {
    @Test
    void WHEN_updateHotel_THEN_notifyUsers() {
        HotelService hotelService = mock(HotelService.class);
        UserService userService = mock(UserService.class);
        NotificationService notificationService = mock(NotificationService.class);

        HotelModel model = new HotelModel("id", "hotel-name", "location-name");
        model.addUserSave(new UserModel("user", "mail", "pass", "link"));
        when(hotelService.getHotelById(anyLong())).thenReturn(model);

        HotelController hotelController = new HotelController(hotelService, userService, notificationService);
        ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
        ArgumentCaptor<NotificationModel> notificationCaptor = ArgumentCaptor.forClass(NotificationModel.class);

        doNothing().when(notificationService).addUserNotification(userCaptor.capture(), notificationCaptor.capture());
        hotelController.updateHotel(new HotelJSON(1L, null, null, null, 0.0, 0, null, null, 0f), "token");
        List<NotificationModel> notificationModelList = notificationCaptor.getAllValues();
        List<UserModel> userModelList = userCaptor.getAllValues();

        assertNotNull(notificationModelList);
        assertNotNull(userModelList);
    }
}
