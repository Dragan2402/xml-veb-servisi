package com.users.service.rjesenje.implementation;

import com.users.model.rjesenje.Rjesenje;
import com.users.repository.RjesenjeRepository;
import com.users.service.rjesenje.IRjesenjeService;
import com.users.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Service
public class RjesenjeService implements IRjesenjeService {

    private final RjesenjeRepository rjesenjeRepository;

    @Override
    public Rjesenje create(Rjesenje rjesenje) throws Exception {
        rjesenje.setId(Utility.getNextId());
        DatatypeFactory df = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlCalendar = df.newXMLGregorianCalendar();
        xmlCalendar.setYear(GregorianCalendar.getInstance().get(Calendar.YEAR));
        xmlCalendar.setMonth(GregorianCalendar.getInstance().get(Calendar.MONTH) + 1);
        xmlCalendar.setDay(GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
        rjesenje.setDatum(xmlCalendar);

        rjesenjeRepository.save("/db/rjesenje","id_"+rjesenje.getId(), rjesenje);

        return findById(rjesenje.getId());
    }

    @Override
    public Rjesenje findById(long id) throws Exception {
        return rjesenjeRepository.findById("/db/rjesenje","id_"+id);
    }
}
