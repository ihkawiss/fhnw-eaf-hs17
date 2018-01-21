package ch.fhnw.edu.rental.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;
import ch.fhnw.edu.rental.model.PriceCategoryNewRelease;
import ch.fhnw.edu.rental.model.PriceCategoryRegular;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;

@Component
public class JdbcPriceCategoryRepository implements PriceCategoryRepository {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public PriceCategory findOne(Long id) {	
		return template.queryForObject(
                "select * from pricecategories where PRICECATEGORY_ID = ?",
                (rs, rowNum) -> createPriceCategory(rs),
                id
        );
	}

	@Override
    public List<PriceCategory> findAll() {
        return template.query(
                "select * from pricecategories",
                (rs, rowNum) -> createPriceCategory(rs)
        );
    }

	@Override
	public PriceCategory save(PriceCategory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(PriceCategory entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// helper to create instance of price category by a ResultSet
    private PriceCategory createPriceCategory(ResultSet rs) throws SQLException{

        String type = rs.getString("PRICECATEGORY_TYPE");

        PriceCategory category = null;

        switch (type) {
            case "Children":
                category = new PriceCategoryChildren();
                break;
            case "NewRelease":
                category = new PriceCategoryNewRelease();
                break;
            case "Regular":
                category = new PriceCategoryRegular();
                break;
        }

        if(category != null)
            category.setId(rs.getLong("PRICECATEGORY_ID"));

        return category;
    }

}
