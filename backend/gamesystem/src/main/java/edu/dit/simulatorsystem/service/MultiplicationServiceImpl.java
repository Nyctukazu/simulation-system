package edu.dit.simulatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import edu.dit.simulatorsystem.dto.MultiplicationRequest;
import edu.dit.simulatorsystem.dto.MultiplicationResponse;
import edu.dit.simulatorsystem.dto.MultiplicationResponse.TableEntry;
import edu.dit.simulatorsystem.service.utilities.MultiplicationService;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    @Override
    public MultiplicationResponse calculateProduct(MultiplicationRequest request) {
        if (request == null) {
            return new MultiplicationResponse(0, new ArrayList<>(), false);
        }

        int target = request.num1();
        List<TableEntry> entries = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            int product = target * i;
            entries.add(new TableEntry(target, i, product));
        }

        return new MultiplicationResponse(target, entries, true);
    }
}
