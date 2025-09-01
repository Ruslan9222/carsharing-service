package by.ruslan.radzevich.carsharingservice.mapper;

import by.ruslan.radzevich.carsharingservice.dto.CreateCardDto;
import by.ruslan.radzevich.model.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CreateCardDto newCard(Card card);
}
