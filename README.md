# USAGE

## Single implementation

```java
@IService
public interface AnService {
	boolean isRun();
}

@Service
public class AnServiceImpl implements AnService {
	@Override
	public boolean isRun() {
		return true;
	}
}
```
```java
import com.meatwork.core.api.di.Service;
import jakarta.inject.Inject;

@IService
public interface AnService2 {
	boolean isRun();
}

@Service
public class AnServiceImpl2 implements AnService2 {

	private final AnService anService;

	@Inject
	public AnServiceImpl2(AnService anService) {
		this.anService = anService;
	}

	@Override
	public boolean isRun() {
		return anService.isRun();
	}
}
```

## Multi implementation

```java
import com.meatwork.core.api.di.Service;
import com.meatwork.core.api.di.IService;

import java.util.Set;

@IService(scope = IService.Scope.MULTIPLE)
public interface Validator {
	boolean isValid();
}


@Service
public class TotoValidator implements Validator {
	@Override
	public boolean isValid() {
		return true;
	}
}

@Service
public class TitiValidator implements Validator {
	@Override
	public boolean isValid() {
		return true;
	}
}

@Service
public class ValidateEntity {

	private final Set<Validator> validatorSet;
	
	@Inject
    public ValidateEntity(Set<Validator> validatorSet) {
		this.validatorSet = validatorSet;
    }

	public checkValidation() {
		if(!validatorSet.stream().allMatch(Validator::isValid)) {
			throw new RuntimeException("not all validator match !");
		}
	}

}
```