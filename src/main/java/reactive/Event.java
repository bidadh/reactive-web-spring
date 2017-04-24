package reactive;

import lombok.Data;

import java.util.Date;

/**
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @date 21/4/17 11:34 AM
 */
@Data(staticConstructor = "of")
public class Event {
  private final long id;
  private final Date when;
}
