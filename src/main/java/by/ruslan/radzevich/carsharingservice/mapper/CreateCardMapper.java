package by.ruslan.radzevich.carsharingservice.mapper;

import by.ruslan.radzevich.carsharingservice.dto.CreateCardDto;
import by.ruslan.radzevich.carsharingservice.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CreateCardMapper {
    public Card createCardToCard(CreateCardDto createCardDto){
        Card card = new Card();
        card.setName(createCardDto.getName());
        card.setNumber(createCardDto.getNumber());
        card.setCv(createCardDto.getCv());
        return card;
    }

}
