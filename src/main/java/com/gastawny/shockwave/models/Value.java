package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "value_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = ValueNum.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueStr.class, name = "string"),
        @JsonSubTypes.Type(value = ValueNum.class, name = "number"),
        @JsonSubTypes.Type(value = ValueText.class, name = "text")
})
public abstract class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private Long id;

    public abstract Object getValue();

    public abstract void setRaw(Object v);

    public void setValue(Object v) {
        if (v instanceof Map) {
            Object inner = ((Map<?, ?>) v).get("value");
            setRaw(inner);
        } else {
            setRaw(v);
        }
    }
}
