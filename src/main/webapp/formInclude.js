/**
 * Created by Terrax on 28-Feb-2016.
 */
$(function() {
// =================================== Candidate ===================================
    /**
     * Display the addCandidate form on Add Candidate button click located in the navigation.
     * Used to enter a new Candidate into the database.
     */
    $('#add-candidate-button').on('click', function() {
        $('.content-container').load('/HREmpower/candidate/addCandidate.html');
    });

    /**
     * Display new Technology and Years fields on button click.
     * Used to add Candidate's years of experience with a Technology.
     */
    $('#add-experience-button').on('click', function() {
        // Add new row
        $.get("/HREmpower/candidate/addExperience.html", function(data) {
            $('#experience-list').append(data);

            // Populate the Technology select box.
            populateTechsOn($('.technology-container').last());
        });
    });

    /**
     * Display new Positions and Company fields on button click.
     * Used to add Candidate's position application.
     */
    $('#add-applications-button').on('click', function() {
        // Add new row
        $.get("/HREmpower/candidate/addApplication.html", function(data) {
            $('#applications-list').append(data);

            // Populate the Position select box.
            populatePositionsOn($('.position-container').last());
        });
    });

    /**
     * Displays the searchCandidate form on Search Candidate button click located in the navigation.
     * Used to search for Candidate(s) based on the provided criteria.
     */
    $('#search-candidate-button').on('click', function() {
        $('.content-container').load('/HREmpower/candidate/searchCandidate.html');
    });

// =================================== Technology ===================================

    /**
     * Display the addTechnology form on Add Technology button click located in the navigation.
     * Used to enter a new Technology into the database.
     */
    $('#add-technology-button').on('click', function() {
        $('.content-container').load('/HREmpower/technology/addTechnology.html');
    });

    /**
     * Displays the searchTechnology form on Search Technology button click located in the navigation.
     * Used to search for Technologies based on the provided criteria.
     */
    $('#search-technology-button').on('click', function() {
        $('.content-container').load('/HREmpower/technology/searchTechnology.html');
    });

// =================================== Company ===================================

    /**
     * Display the addCompany form on Add Company button click located in the navigation.
     * Used to enter a new Company into the database.
     */
    $('#add-company-button').on('click', function() {
        $('.content-container').load('/HREmpower/company/addCompany.html');
    });

    /**
     * Displays the searchCompany form on Search Company button click located in the navigation.
     * Used to search for Companies based on the provided criteria.
     */
    $('#search-company-button').on('click', function() {
        $('.content-container').load('/HREmpower/company/searchCompany.html');
    });

// =================================== HR ===================================

    /**
     * Display the addHR form on Add HR button click located in the navigation.
     * Used to enter a new HR into the database.
     */
    $('#add-hr-button').on('click', function() {
        $('.content-container').load('/HREmpower/hr/addHR.html');

    });

    /**
     * Display new Company fields on button click.
     * Used to add Company to the HR.
     */
    $('#add-hr-company-button').one('click', function() {
        // Add new row
        $.get("/HREmpower/hr/addCompanies.html", function(data) {
            $('#company-list').append(data);

            // Populate the Company select box.
            populateCompaniesOn($('.company-container').last());
        });
    });

    /**
     * Displays the searchHR form on Search HR button click located in the navigation.
     * Used to search for HRs based on the provided criteria.
     */
    $('#search-hr-button').on('click', function() {
        $('.content-container').load('/HREmpower/hr/searchHR.html');
    });

// =================================== Position ===================================

    /**
     * Display the addPosition form on Add Position button click located in the navigation.
     * Used to enter a new Position into the database.
     */
    $('#add-position-button').on('click', function() {
        $('.content-container').load('/HREmpower/position/addPosition.html');
    });

    /**
     * Display new Company fields on button click.
     * Used to add Company to the Position.
     */
    $('#add-position-company-button').one('click', function() {
        // Add new row
        $.get("/HREmpower/position/addPositionCompanies.html", function(data) {
            $('#position-company-list').append(data);

            // Populate the Company select box.
            populateCompaniesOn($('.position-company-select-container').last());
        });
    });

    /**
     * Display new HR fields on button click.
     * Used to add HR to the Position.
     */
    $('#add-position-hr-button').one('click', function() {
        // Add new row
        $.get("/HREmpower/position/addHRs.html", function(data) {
            $('#position-hr-list').append(data);

            // Populate the HR select box.
            populateHROn($('.position-hr-select-container').last());
        });
    });

    /**
     * Display new Requirement fields on button click.
     * Used to add Requirement to the Position.
     */
    $('#add-position-requirement-button').on('click', function() {
        // Add new row
        $.get("/HREmpower/position/addRequirement.html", function(data) {
            $('#position-requirement-list').append(data);

            // Populate the Requirement select box.
            populateRequirementOn($('.position-requirement-select-container').last());
        });
    });

    /**
     * Displays the searchPosition form on Search Position button click located in the navigation.
     * Used to search for Positions based on the provided criteria.
     */
    $('#search-position-button').on('click', function() {
        $('.content-container').load('/HREmpower/position/searchPosition.html');
    });

    /**
     * Displays the searchForMatch form on Search For Match button click located in the navigation.
     * Used to search for best match for a Position based on the provided criteria.
     */
    $('#search-for-match-button').on('click', function() {
        $('.content-container').load('/HREmpower/position/searchForMatch.html');
    });
});