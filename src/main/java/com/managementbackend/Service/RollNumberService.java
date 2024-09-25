package com.managementbackend.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.managementbackend.Model.RollNumber;
import com.managementbackend.Model.RollNumber.RollItem;
import com.managementbackend.Repo.RollNumberRepo;

@Service
public class RollNumberService {
    @Autowired
    private RollNumberRepo rollNumberRepo;

    public void SaveRollNumber(String roll, String name)
    {
        if (rollNumberRepo.findAll().size() == 0) {
            RollNumber rollNumber = new RollNumber();
            List<RollItem> rollItemList = new ArrayList<RollItem>(); // The List will be empty if the database is Empty...
            rollItemList.add(new RollItem(roll, name)); // The rollItem is created using the enclosing instance of RollNumber
            rollNumber.setIndexList(rollItemList);
            rollNumberRepo.save(rollNumber); // The rollNumber data is saved...
        } else {
            RollNumber rollNumber = rollNumberRepo.findAll().get(0); // Otherwise getting the first Query...
            List<RollItem> rollItemList = rollNumber.getIndexList(); // The List will not be empty in this case...
            rollItemList.add(new RollItem(roll, name)); // The rollItem is created using the enclosing instance of RollNumber
            rollNumber.setIndexList(rollItemList);
            rollNumberRepo.save(rollNumber); // The rollNumber data is saved...
        }
    }

    public List<RollItem> GetRollNumberList() {
        if (rollNumberRepo.findAll().size() == 0) // If the List is Empty...
        {
            return new ArrayList<RollItem>();
        } else { // Otherwise...
            RollNumber rollNumber = rollNumberRepo.findAll().get(0); // Getting the first Query...
            return rollNumber.getIndexList();
        }
    }

    @Scheduled(cron="0 0 */6 * * *") // Run after every 6 hours...
    public void SortRollNumbers() {
        if (rollNumberRepo.findAll().size() == 0) // If the List is Empty...
        {
            return;
        } else { // Otherwise...
            RollNumber rollNumber = rollNumberRepo.findAll().get(0); // Getting the first Query...
            List<RollItem> rollItemList = rollNumber.getIndexList();
            Collections.sort(rollItemList, new Comparator<RollItem>() {
                @Override
                public int compare(RollItem o1, RollItem o2) {
                    return o1.getRoll().compareTo(o2.getRoll());
                }
            });
            rollNumber.setIndexList(rollItemList);
            rollNumberRepo.save(rollNumber);
        }
    }
}
