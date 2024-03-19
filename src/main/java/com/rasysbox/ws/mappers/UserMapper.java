package com.rasysbox.ws.mappers;

import com.rasysbox.ws.dto.address.AddressDto;
import com.rasysbox.ws.dto.address.request.AddressRequestDto;
import com.rasysbox.ws.dto.company.CompanyDto;
import com.rasysbox.ws.dto.company.request.CompanyRequestDto;
import com.rasysbox.ws.dto.geo.GeoDto;
import com.rasysbox.ws.dto.geo.request.GeoRequestDto;
import com.rasysbox.ws.dto.user.request.UserRequestDto;
import com.rasysbox.ws.dto.user.UserDto;
import com.rasysbox.ws.dto.user.response.UserResponseDto;
import com.rasysbox.ws.entity.Address;
import com.rasysbox.ws.entity.Company;
import com.rasysbox.ws.entity.Geo;
import com.rasysbox.ws.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rasysbox.ws.utils.Shield.blindLong;
import static com.rasysbox.ws.utils.Shield.blindStr;

@Component
public class UserMapper {

    public static List<UserDto> userDtoListFromUsers(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(userDtoFromUser(user));
        }

        return userDtoList;
    }

    public static UserDto userDtoFromUser(User user) {
        return UserDto.builder()
                // User information
                .id(blindLong(user.getId()))
                .name(blindStr(user.getName()))
                .username(blindStr(user.getUsername()))
                .email(blindStr(user.getEmail()))
                .phone(blindStr(user.getPhone()))
                .website(blindStr(user.getWebsite()))
                // Address Information
                .address(AddressDto.builder()
                        .id(blindLong(user.getAddress().getId()))
                        .city(blindStr(user.getAddress().getCity()))
                        .zipcode(blindStr(user.getAddress().getZipcode()))
                        // Geographical information
                        .geo(GeoDto.builder()
                                .id(blindLong(user.getAddress().getGeo().getId()))
                                .lat(blindStr(user.getAddress().getGeo().getLat()))
                                .lng(blindStr(user.getAddress().getGeo().getLng()))
                                .build())
                        .street(blindStr(user.getAddress().getStreet()))
                        .suite(blindStr(user.getAddress().getSuite()))
                        .build())
                // Company business information
                .company(CompanyDto.builder()
                        .id(blindLong(user.getCompany().getId()))
                        .name(blindStr(user.getCompany().getName()))
                        .bs(blindStr(user.getCompany().getBs()))
                        .catchPhrase(user.getCompany().getCatchPhrase())
                        .build())
                .build();
    }

    public static User userRequestDto(UserRequestDto user) {
        // Geographical information
        Geo geo = new Geo();
        //geo.setId(user.getAddress().getGeo().getId());
        geo.setLat(user.getAddress().getGeo().getLat());
        geo.setLng(user.getAddress().getGeo().getLng());
        // Address Information
        Address address = new Address();
        //address.setId(user.getAddress().getId());
        address.setCity(user.getAddress().getCity());
        address.setSuite(user.getAddress().getSuite());
        address.setStreet(user.getAddress().getStreet());
        address.setZipcode(user.getAddress().getZipcode());
        address.setGeo(geo);
        // Company business information
        Company company = new Company();
        //company.setId(user.getCompany().getId());
        company.setName(user.getCompany().getName());
        company.setBs(user.getCompany().getBs());
        company.setCatchPhrase(user.getCompany().getCatchPhrase());
        // User information
        User userData = new User();
        userData.setName(user.getName());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());
        userData.setWebsite(user.getWebsite());
        userData.setAddress(address);
        userData.setCompany(company);

        return userData;
    }

    public static User userUpdateRequestDto(UserRequestDto user,
                                            Long userId,
                                            Long addressId,
                                            Long geoId,
                                            Long companyId) {
        // Geographical information
        Geo geo = new Geo();
        geo.setId(geoId);
        geo.setLat(user.getAddress().getGeo().getLat());
        geo.setLng(user.getAddress().getGeo().getLng());
        // Address Information
        Address address = new Address();
        address.setId(addressId);
        address.setCity(user.getAddress().getCity());
        address.setSuite(user.getAddress().getSuite());
        address.setStreet(user.getAddress().getStreet());
        address.setZipcode(user.getAddress().getZipcode());
        address.setGeo(geo);
        // Company business information
        Company company = new Company();
        company.setId(companyId);
        company.setName(user.getCompany().getName());
        company.setBs(user.getCompany().getBs());
        company.setCatchPhrase(user.getCompany().getCatchPhrase());
        // User information
        User userData = new User();
        userData.setId(userId);
        userData.setName(user.getName());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());
        userData.setWebsite(user.getWebsite());
        userData.setAddress(address);
        userData.setCompany(company);

        return userData;
    }

    public static UserRequestDto userRequestDtoFromUserRequestDto(UserRequestDto user) {
        return UserRequestDto.builder()
                .name(blindStr(user.getName()))
                .username(blindStr(user.getUsername()))
                .email(blindStr(user.getEmail()))
                .address(AddressRequestDto.builder()
                        .city(blindStr(user.getAddress().getCity()))
                        .zipcode(blindStr(user.getAddress().getZipcode()))
                        .geo(GeoRequestDto.builder()
                                .lat(blindStr(user.getAddress().getGeo().getLat()))
                                .lng(blindStr(user.getAddress().getGeo().getLng()))
                                .build())
                        .street(blindStr(user.getAddress().getStreet()))
                        .suite(blindStr(user.getAddress().getSuite()))
                        .build())
                .phone(blindStr(user.getPhone()))
                .company(CompanyRequestDto.builder()
                        .name(blindStr(user.getCompany().getName()))
                        .bs(blindStr(user.getCompany().getBs()))
                        .catchPhrase(user.getCompany().getCatchPhrase())
                        .build())
                .website(blindStr(user.getWebsite()))
                .build();
    }

    public static UserResponseDto UserResponseDtoFromOptionalUser(Optional<User> user,
                                                                  String status,
                                                                  String message) {
        if (user.isPresent()) {
            User item = user.get();
            return UserResponseDto.builder()
                    .status(blindStr(status))
                    .message(blindStr(message))
                    .user(UserDto.builder()
                    // User information
                    .id(blindLong(item.getId()))
                    .name(blindStr(item.getName()))
                    .username(blindStr(item.getUsername()))
                    .email(blindStr(item.getEmail()))
                    .phone(blindStr(item.getPhone()))
                    .website(blindStr(item.getWebsite()))
                    // Address Information
                    .address(AddressDto.builder()
                            .id(blindLong(item.getAddress().getId()))
                            .city(blindStr(item.getAddress().getCity()))
                            .zipcode(blindStr(item.getAddress().getZipcode()))
                            // Geographical information
                            .geo(GeoDto.builder()
                                    .id(blindLong(item.getAddress().getGeo().getId()))
                                    .lat(blindStr(item.getAddress().getGeo().getLat()))
                                    .lng(blindStr(item.getAddress().getGeo().getLng()))
                                    .build())
                            .street(blindStr(item.getAddress().getStreet()))
                            .suite(blindStr(item.getAddress().getSuite()))
                            .build())
                    // Company business information
                    .company(CompanyDto.builder()
                            .id(blindLong(item.getCompany().getId()))
                            .name(blindStr(item.getCompany().getName()))
                            .bs(blindStr(item.getCompany().getBs()))
                            .catchPhrase(item.getCompany().getCatchPhrase())
                            .build())
                            .build())
                    .build();
        }

        return UserResponseDto.builder().build();
    }

}
