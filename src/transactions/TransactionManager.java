package transactions;

import java.util.*;

public class TransactionManager {


    private Set<Region> regionSet = new HashSet<>();
    private Set<Carrier> carrierSet = new HashSet<>();
    private List<Offer> offers = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();

    //R1
    public List<String> addRegion(String regionName, String... placeNames) {

        Region region = new Region(regionName, placeNames);
        regionSet.add(region);

        List<String> placeNamesList = new ArrayList<>();
        for (String placeName : placeNames) {
            if (!placeNamesList.contains(placeName)) {
                placeList.add(placeName);
                placeNamesList.add(placeName);
            }
        }
        Collections.sort(placeNamesList);
        return placeNamesList;
    }

    public List<String> addCarrier(String carrierName, String... regionNames) {
        Carrier carrier = new Carrier(carrierName, regionNames);
        carrierSet.add(carrier);

        List<String> carrierRegionList = new ArrayList<>();

        for (String regionName : regionNames) {
            if (!carrierRegionList.contains(regionName)) {
                carrierRegionList.add(regionName);
            }
        }
        Collections.sort(carrierRegionList);
        return carrierRegionList;
    }

    public List<String> getCarriersForRegion(String regionName) {

        List<String> carrierList = new ArrayList<>();
        for (Carrier carrier : carrierSet) {
            carrierList.add(carrier.getCarrierName());
        }
        Collections.sort(carrierList);
        return carrierList;
    }

    //R2
    List<String> placeList = new ArrayList<>();

    public void addRequest(String requestId, String placeName, String productId)
            throws TMException {
        boolean placeNameExist = false;

        for (String place : placeList) {
            if (place.equals(placeName)) {
                placeNameExist = true;
            }
        }
        if (!placeNameExist) {
            throw new TMException("1");
        }
        for (Request request : requests) {
            if (request.getRequestID().equals(requestId)) {
                throw new TMException("Duplicated");
            }
        }
        Request request = new Request(requestId, placeName, productId);
        requests.add(request);
    }

    public void addOffer(String offerId, String placeName, String productId)
            throws TMException {
        boolean placeNameExist = false;

        for (String place : placeList) {
            if (place.equals(placeName)) {
                placeNameExist = true;
            }
        }
        if (!placeNameExist) {
            throw new TMException("2");
        }
        for (Offer offer : offers) {
            if (offer.getOfferID().equals(offerId)) {
                throw new TMException("Duplicated");
            }
        }
        Offer offer = new Offer(offerId, placeName, productId);
        offers.add(offer);
    }


    //R3
    public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)
            throws TMException {
    }

    public boolean evaluateTransaction(String transactionId, int score) {
        return false;
    }

    //R4
    public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
        return new TreeMap<Long, List<String>>();
    }

    public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
        return new TreeMap<String, Integer>();
    }

    public SortedMap<String, Long> nTPerProduct() {
        return new TreeMap<String, Long>();
    }

    private static class ByNameRegionComparator implements Comparator<Region> {

        @Override
        public int compare(Region o1, Region o2) {
            return o1.getRegionName().compareTo(o2.getRegionName());
        }
    }

    private static class ByNameCarrierComparator implements Comparator<Carrier> {

        @Override
        public int compare(Carrier o1, Carrier o2) {
            return o1.getCarrierName().compareTo(o2.getCarrierName());
        }
    }
}

