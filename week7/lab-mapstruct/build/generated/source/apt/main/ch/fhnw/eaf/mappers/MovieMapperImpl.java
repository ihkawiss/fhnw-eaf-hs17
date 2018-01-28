package ch.fhnw.eaf.mappers;

import ch.fhnw.eaf.rental.dto.RentalDto;
import ch.fhnw.eaf.rental.dto.UserDto;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-01-28T16:56:23+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRentalIds( rentalSetToLongSet( user.getRentals() ) );
        if ( user.getId() != null ) {
            userDto.setId( user.getId() );
        }
        userDto.setLastName( user.getLastName() );
        userDto.setFirstName( user.getFirstName() );

        return userDto;
    }

    @Override
    public RentalDto rentalToRentalDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalDto rentalDto = new RentalDto();

        if ( rental.getId() != null ) {
            rentalDto.setId( rental.getId() );
        }
        rentalDto.setDays( rental.getDays() );

        return rentalDto;
    }

    protected Set<Long> rentalSetToLongSet(Set<Rental> set) {
        if ( set == null ) {
            return null;
        }

        Set<Long> set1 = new HashSet<Long>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Rental rental : set ) {
            set1.add( rentalToLong( rental ) );
        }

        return set1;
    }
}
