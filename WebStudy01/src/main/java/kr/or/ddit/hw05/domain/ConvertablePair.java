package kr.or.ddit.hw05.domain;

import java.util.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = { "from", "to" })
public class ConvertablePair {
    private final Currency from;
    private final Currency to;
}
