package personal.Tu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.Tu.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
