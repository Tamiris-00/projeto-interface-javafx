package model.entities;

import java.io.Serializable;

public record Department(Integer id, String name) implements Serializable {

	private static final long serialVersionUID = 1L;

}
