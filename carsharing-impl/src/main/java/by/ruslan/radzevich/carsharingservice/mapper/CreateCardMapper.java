//package by.ruslan.radzevich.carsharingservice.mapper;
//
//import by.ruslan.radzevich.carsharingservice.dto.CreateCardDto;
//import by.ruslan.radzevich.carsharingservice.model.Card;
//import by.ruslan.radzevich.carsharingservice.model.User;
//import by.ruslan.radzevich.carsharingservice.repository.UserRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CreateCardMapper {
//    public Card createCardToCard(CreateCardDto createCardDto) {
//        Card card = new Card();
//        User user = new User();
//        card.setName(createCardDto.getName());
//        card.setNumber(createCardDto.getNumber());
//        card.setCv(createCardDto.getCv());
//        card.setUser(user);
//        return card;
//    }
//
//}
