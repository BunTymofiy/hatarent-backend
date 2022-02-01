package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.businesslayer.PropertyServiceImpl;
import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {PropertyServiceImpl.class})
@ActiveProfiles({"test"})
public class PropertyServiceTest {

    @MockBean
    private PropertyRepository propertyRepository;

    @Autowired
    @InjectMocks
    private PropertyServiceImpl propertyService;
    private Property property;
    private Property property2;
    private Property property3;
    private List<Property> propertyList;
    private UUID id;
    private UUID id2;
    private UUID id3;

    @Test
    void contextLoads(){}

    @BeforeEach
    void setUp(){
        id = UUID.fromString("2ff4fe29-d8ab-476a-9783-a3ac48bdcfd8");
        id2 = UUID.fromString("f7687281-6bbf-4498-b40d-d1ecbeab6bcb");
        id3 = UUID.fromString("4ad00d0b-c593-441b-a808-5b17e148142d");
        property = new Property(id, null, new Address(), 5, "description", "title", "email", "contact_person", new ArrayList<>());
        property2 = new Property(id2, null, new Address(), 5, "description", "title", "email", "contact_person", new ArrayList<>());
        property3 = new Property(id3, null, new Address(), 5, "description", "title", "email", "contact_person", new ArrayList<>());
        propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        propertyList.add(property3);
    }
    @AfterEach
    void tearDown(){
        propertyRepository.deleteAll();
        property = property2 = property3 = null;
        propertyList = null;
        id = id2 = id3 = null;
    }

//    private static List<Property> generateProperties(int num) {
//        List<Property> property = new ArrayList<>();
//        for (int i = 0; i < num; i++) {
//            property.add(Property.builder()
//                    .title("title"+i)
//                    .description("description"+i)
//                    .email("email"+i+"@gmail.com")
//                    .contact_person("contact_person"+i)
//                    .guestLimit(5)
//                    .build());
//        }
//        return property;
//    }


    @Test
    void givenPropertyToAddShouldReturnAddedProperty(){
        //stubbing
        when(propertyRepository.save(any())).thenReturn(property);
        propertyService.create(property);
        verify(propertyRepository,times(1)).save(any());
    }
    @Test
    public void GivenGetAllPropertyShouldReturnListOfAllProperty(){
        propertyRepository.save(property);
        //stubbing mock to return specific data
        when(propertyRepository.findAll()).thenReturn(propertyList);
        List<Property> productList1 =propertyService.findAll();
        assertEquals(productList1,productList1);
        verify(propertyRepository,times(1)).save(property);
        verify(propertyRepository,times(1)).findAll();
    }
    @Test
    public void givenIdThenShouldReturnPropertyOfThatId() {
        Mockito.when(propertyRepository.findById(UUID.fromString("2ff4fe29-d8ab-476a-9783-a3ac48bdcfd8"))).thenReturn(Optional.ofNullable(property));
        assertThat(propertyService.findByUuid(property.getUuid()).get()).isEqualTo(property);
    }
}

