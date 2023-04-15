package parser.dao;

import parser.entity.Claim;

import java.util.List;

public interface ClaimRepo {
    void save(Claim claim);
    void save(List<Claim> claimList);
}
